package ru.yourbunny.yourbunny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindLocationService {
    @Autowired
    private ProfileService profileService;
}
