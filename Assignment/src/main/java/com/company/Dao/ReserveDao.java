package com.company.Dao;

import com.company.Bean.Reserve;

import java.util.List;

public interface ReserveDao {
    int addReserve(Reserve reserve);

    List<Reserve> QueryReserve(String labname);

    int UpdateReserve(String ID, String result);

    int DeleteReserveByStudent(String student);

    int DeleteReserveByLa(String laName);
}
