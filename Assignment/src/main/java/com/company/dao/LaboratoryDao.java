package com.company.dao;

import com.company.pojo.Laboratory;

import java.util.List;

public interface LaboratoryDao {

    int addLaboratoryDao(Laboratory laboratory);

    List<Laboratory> QueryLaboratory(String laboratoryName);

    int UpdateLaboratory(Laboratory laboratory);

    int UpdateLaboratoryStates(String laboratoryName);

    int DeleteLaboratory(String ID);

}
