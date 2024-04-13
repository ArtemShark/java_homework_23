package com.company.homework_lesson_23.utils;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Log4j2
@Component
public class Helper {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getStringInputByLength(int maxLength, String mess) {
        while (true) {
            try {
                System.out.print(mess);
                final String inputString = SCANNER.nextLine();

                if (inputString.length() > maxLength) {
                    throw new InputMismatchException();
                }

                return inputString;
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect line length!");
            }
        }
    }

    public long getCorrectIdInput(List<Long> longList, String mess) {
        while (true) {
            try {
                System.out.print(mess);
                final long id = Long.parseLong(SCANNER.nextLine());

                if (!longList.contains(id)) {
                    throw new InputMismatchException();
                }

                return id;
            }
            catch (InputMismatchException e) {
                log.info(" Incorrect number!");
            }
        }
    }
}

