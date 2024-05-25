package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class dbInit implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public dbInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) {

        if (conditionRepository.count() == 0) {
            List<Condition> conditions = Arrays.stream(ConditionName.values())
                    .map(conditionName -> new Condition().setName(conditionName))
                    .toList();
            this.conditionRepository.saveAll(conditions);
        }
    }
}
