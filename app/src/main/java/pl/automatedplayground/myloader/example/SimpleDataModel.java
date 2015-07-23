package pl.automatedplayground.myloader.example;
/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/

import pl.automatedplayground.myloader.loader.data.DataModel;

public class SimpleDataModel extends DataModel {
    public String data;

    public SimpleDataModel setData(String test) {
        data = test;
        return this;
    }
}
