package com.cegeka.switchfully.security.spring;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Feature {
    GETDEPLOYEDARMYINFO(Roles.GENERAL, Roles.PRIVATE),
    JOINARMY(Roles.CIVILIAN),
    PROMOTEPRIVATE(Roles.HUMAN_RELATIONSHIPS),
    DISCHARGESOLDIER(Roles.HUMAN_RELATIONSHIPS),
    LAUNCHNUKES(Roles.GENERAL);

    private final List<Roles> roles = new ArrayList<>();

    Feature(Roles role1, Roles role2) {
        roles.add(role1);
        roles.add(role2);
    }

    Feature(Roles role1) {
        roles.add(role1);
    }

    public static List<Feature> getFeaturesForRoles(List<Roles> givenRoles) {
        List<Feature> features = new ArrayList<>();

        givenRoles.forEach(
                role -> Arrays.stream(
                        values()).forEach(
                        feature -> {
                            if (feature.roles.contains(role)) {
                                features.add(feature);
                            }
                        }));

        return features;
    }
}
