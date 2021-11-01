package com.ratz.service;


import com.ratz.entity.ItemOrdered;

import java.util.List;

public interface ItemOrderedService {

    ItemOrdered saveItemOrdered(ItemOrdered item);
    List<ItemOrdered> saveAllItemOrdered(List<ItemOrdered> orderedList);
}
