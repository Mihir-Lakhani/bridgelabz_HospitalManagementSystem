package com.example.HospitalManagementSystem.enums;



public enum Gender {

    MALE,
    FEMALE;

    public Gender fromString(String gender){
        for (Gender genderEnum : Gender.values()){
            if (gender.equalsIgnoreCase(genderEnum.name())){
                return genderEnum;
            }
        }

        return null;
    }

}