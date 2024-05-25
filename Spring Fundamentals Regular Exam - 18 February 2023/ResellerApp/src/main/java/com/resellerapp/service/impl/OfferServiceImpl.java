package com.resellerapp.service.impl;

import com.resellerapp.init.LoggedUser;
import com.resellerapp.model.dto.home.AllOtherOffersDTO;
import com.resellerapp.model.dto.home.BoughtOffersDTO;
import com.resellerapp.model.dto.home.HomeViewDTO;
import com.resellerapp.model.dto.home.MyOffersDTO;
import com.resellerapp.model.dto.offer.OfferAddDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ConditionRepository conditionRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(OfferAddDTO offerAddDTO) {
        Condition condition = this.conditionRepository.findByName(offerAddDTO.getName());
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername());

        if (user != null && condition != null) {
            Offer offer = this.modelMapper.map(offerAddDTO, Offer.class);
            offer.setCondition(condition);
            offer.setCreatedBy(user);

            this.offerRepository.save(offer);
            return true;
        }
        return false;
    }

    @Override
    public HomeViewDTO getHomePageOffersView() {

        String username = loggedUser.getUsername();

        List<MyOffersDTO> myOffers = getMyOffersDTOS(username);

        List<BoughtOffersDTO> boughtOffers = getBoughtOffersDTOS(username);

        List<AllOtherOffersDTO> allOtherOffers = getAllOtherOffersDTOS(username);

        return new HomeViewDTO(myOffers, boughtOffers, allOtherOffers);
    }

    @Override
    public void remove(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public void buy(Long id) {
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername());
        Offer offer = this.offerRepository.findById(id).get();

        offer.setBoughtBy(user);

        this.offerRepository.save(offer);

    }


    private List<AllOtherOffersDTO> getAllOtherOffersDTOS(String username) {
        return this.offerRepository.findByBoughtByIsNullAndCreatedByUsernameIsNot(username)
                .stream()
                .map(offer -> this.modelMapper.map(offer, AllOtherOffersDTO.class))
                .toList();
    }

    private List<BoughtOffersDTO> getBoughtOffersDTOS(String username) {
        return this.offerRepository
                .findByBoughtByIsNotNullAndBoughtByUsername(username)
                .stream()
                .map(offer -> this.modelMapper.map(offer, BoughtOffersDTO.class))
                .toList();
    }

    private List<MyOffersDTO> getMyOffersDTOS(String username) {
        return this.offerRepository.findByCreatedByUsernameAndBoughtByIsNull(username)
                .stream()
                .map(offer -> this.modelMapper.map(offer, MyOffersDTO.class))
                .toList();
    }
}
