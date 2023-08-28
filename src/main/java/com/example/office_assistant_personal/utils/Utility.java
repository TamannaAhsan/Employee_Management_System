package com.example.office_assistant_personal.utils;

import com.example.office_assistant_personal.entity.enums.RoleStatus;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utility {
    public static void copyNonListAndIdAndCreatedDateProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getListPropertyNames(src));
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static void checkIfUserIsAdmin() throws ApiSystemException {
         /*String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElse(null);*/
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!loggedInUser.getRoleStatus().equals(RoleStatus.ADMIN))
            throw new ApiSystemException("Only Admin can create user");
    }
    private static String[] getListPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            if (pd.getPropertyType().isAssignableFrom(List.class))
                emptyNames.add(pd.getName());
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        emptyNames.addAll(Arrays.asList("id", "createdDate", "createdBy"));
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
