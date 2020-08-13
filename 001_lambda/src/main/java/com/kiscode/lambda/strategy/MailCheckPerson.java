package com.kiscode.lambda.strategy;


import com.kiscode.lambda.model.Person;

/**
 * Description: 女性筛选
 * Author: keno
 * CreateDate: 2020/8/13 21:49
 */

public class MailCheckPerson implements CheckPerson {
    @Override
    public boolean check(Person person) {
        return person.getGender() == Person.Sex.MALE;
    }
}
