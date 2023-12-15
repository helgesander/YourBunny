package ru.yourbunny.yourbunny.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileNotFoundException extends RuntimeException {

}
