package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.home.*;
import com.paintingscollectors.model.dto.painting.AddPaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    public PaintingServiceImpl(PaintingRepository paintingRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, StyleRepository styleRepository) {
        this.paintingRepository = paintingRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }

    @Override
    public boolean createPainting(AddPaintingDTO addPaintingDTO) {
        Optional<Style> optionalStyle =
                styleRepository.findByStyleName(addPaintingDTO.getStyleName());
        Optional<User> optionalUser = getUser();

        if (optionalStyle.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Painting painting = modelMapper.map(addPaintingDTO, Painting.class);
        painting.setStyle(optionalStyle.get());
        painting.setOwner(optionalUser.get());

        paintingRepository.save(painting);

        return true;
    }

    @Override
    @Transactional
    public HomeViewDTO getAllPaintings() {

        Optional<User> optionalUser = getUser();

        if (optionalUser.isEmpty()) {
            return new HomeViewDTO();
        }

        User user = optionalUser.get();

        List<Painting> allPaintings = paintingRepository.findAll();

        List<MyPaintingsDTO> myPaintings = getMyPaintings(allPaintings, user);

        List<AllOtherPaintingsDTO> allOthers = getAllOtherPaintings(allPaintings, user);

        List<MyFavouritePaintingsDTO> myFavourites = getMyFavouritePaintings(user);

        List<MostRatedDTO> mostRated = getMostRatedPaintings(allPaintings);

        return new HomeViewDTO(myPaintings, allOthers, myFavourites, mostRated);
    }


    @Override
    @Transactional
    public void remove(Long id) {
        Optional<User> optionalUser = getUser();
        Optional<Painting> optionalPainting = getPainting(id);

        if (optionalUser.isEmpty()) {
            return;
        }

        if (optionalPainting.isEmpty()) {
            return;
        }

        Painting painting = optionalPainting.get();
        Boolean isAddedToFavourites = painting.getFavourite();

        if (isAddedToFavourites) {
            return;
        }

        removeAllPaintingsFromUsersRatedPaintings(painting);
        paintingRepository.delete(painting);
    }

    @Override
    @Transactional
    public void addToFavourite(Long id) {
        Optional<User> optionalUser = getUser();
        Optional<Painting> optionalPainting = getPainting(id);

        if (optionalUser.isEmpty()) {
            return;
        }

        if (optionalPainting.isEmpty()) {
            return;
        }

        User user = optionalUser.get();
        Painting painting = optionalPainting.get();

        if (user.getFavouritePaintings().contains(painting)) {
            return;
        }

        painting.setFavourite(true);

        paintingRepository.save(painting);

        user.getFavouritePaintings().add(painting);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFavourite(Long id) {
        Optional<Painting> optionalPainting = getPainting(id);
        Optional<User> optionalUser = getUser();

        if (optionalUser.isEmpty()) {
            return;
        }

        if (optionalPainting.isEmpty()) {
            return;
        }

        User user = optionalUser.get();
        Painting painting = optionalPainting.get();

        user.getFavouritePaintings().remove(painting);
        userRepository.save(user);

        if (!isPaintingFavourite(painting)) {
            painting.setFavourite(false);
            paintingRepository.save(painting);
        }
    }

    @Override
    @Transactional
    public void vote(Long id) {
        Optional<User> optionalUser = getUser();
        Optional<Painting> optionalPainting = getPainting(id);

        if (optionalPainting.isEmpty()) {
            return;
        }
        if (optionalUser.isEmpty()) {
            return;
        }

        User user = optionalUser.get();
        Painting painting = optionalPainting.get();

        if (user.getRatedPaintings().contains(painting)) {
            return;
        }
        painting.setVotes(painting.getVotes() + 1);
        user.getRatedPaintings().add(painting);
        userRepository.save(user);
        paintingRepository.save(painting);
    }

    private List<MostRatedDTO> getMostRatedPaintings(List<Painting> allPaintings) {
        return allPaintings
                .stream()
                .sorted(Comparator.comparing(Painting::getVotes).reversed())
                .sorted(Comparator.comparing(Painting::getName))
                .limit(2)
                .filter(painting -> painting.getVotes() > 0)
                .map(painting -> modelMapper.map(painting, MostRatedDTO.class))
                .toList();
    }

    private List<MyFavouritePaintingsDTO> getMyFavouritePaintings(User user) {
        return user.getFavouritePaintings().stream()
                .map(painting -> modelMapper.map(painting, MyFavouritePaintingsDTO.class))
                .toList();
    }

    private List<AllOtherPaintingsDTO> getAllOtherPaintings(List<Painting> allPaintings, User user) {
        return allPaintings.stream()
                .filter(painting -> !painting.getOwner().getUsername().equals(user.getUsername()))
                .map(painting -> modelMapper.map(painting, AllOtherPaintingsDTO.class))
                .toList();
    }

    private List<MyPaintingsDTO> getMyPaintings(List<Painting> allPaintings, User user) {
        return allPaintings.stream()
                .filter(painting -> painting.getOwner().getUsername().equals(user.getUsername()))
                .map(painting -> modelMapper.map(painting, MyPaintingsDTO.class))
                .toList();
    }

    private void removeAllPaintingsFromUsersRatedPaintings(Painting painting) {
        userRepository.findAll()
                .stream()
                .map(User::getRatedPaintings)
                .filter(paintings -> paintings.contains(painting))
                .forEach(paintings -> paintings.remove(painting));
    }

    private boolean isPaintingFavourite(Painting paintingToSearch) {
        return userRepository.findAll().stream()
                .map(User::getRatedPaintings)
                .anyMatch(paintings -> paintings.contains(paintingToSearch));
    }

    private Optional<User> getUser() {
        return userRepository.findByUsername(currentUser.getUsername());
    }

    private Optional<Painting> getPainting(Long id) {
        return paintingRepository.findById(id);
    }
}
