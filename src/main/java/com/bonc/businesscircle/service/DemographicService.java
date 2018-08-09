package com.bonc.businesscircle.service;

public interface DemographicService {

    Object getSexNumAndAge(String uuid, String date) throws Exception;

   /* Object getAgeNum(String uuid, String date) throws Exception;*/

    Object getWorkNum(String uuid, String date) throws Exception;
}
