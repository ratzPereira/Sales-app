package com.ratz.service;

import com.ratz.entity.ItemOrdered;
import com.ratz.repository.ItemsOrderedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOrderedServiceImpl implements ItemOrderedService {


    private final ItemsOrderedRepository itemsOrderedRepository;

    public ItemOrderedServiceImpl(ItemsOrderedRepository itemsOrderedRepository) {
        this.itemsOrderedRepository = itemsOrderedRepository;
    }


    @Override
    public ItemOrdered saveItemOrdered(ItemOrdered item) {
       return itemsOrderedRepository.save(item);
    }

    @Override
    public List<ItemOrdered> saveAllItemOrdered(List<ItemOrdered> orderedList) {
        return itemsOrderedRepository.saveAll(orderedList);
    }


}
