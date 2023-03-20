package com.musala.deliverydrones;

import java.util.List;

import lombok.Data;

@Data
public class ListResponse<T> {
    private List<T>  results;
}
