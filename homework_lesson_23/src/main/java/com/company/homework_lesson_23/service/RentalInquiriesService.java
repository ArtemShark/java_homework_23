package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.model.RentalInquiries;

public interface RentalInquiriesService extends CRUDInterface<RentalInquiries> {
    boolean existsRentalInquiriesByFlatId(long flatId);
}
