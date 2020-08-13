package com.kiscode.lambda.strategy;


import com.kiscode.lambda.model.Person;


/**
 * Description: 年龄区间过滤
 * Author: keno
 * CreateDate: 2020/8/13 21:47
 */

public class AgeCheckPerson implements CheckPerson {
    @Override
    public boolean check(Person person) {
        return person.getAge() > 20 && person.getAge() < 30;
    }
}
