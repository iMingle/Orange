package org.mingle.orange.designpattern.adapter;

public class AdapterImpl implements Adapter {
    private Adaptee adaptee;
    
    public AdapterImpl(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public PersonBasic getBasicInfo() {
        PersonBasic basic = new PersonBasic();
        
        basic.setName(this.adaptee.getPersonInfo().getName());
        basic.setAge(this.adaptee.getPersonInfo().getAge());
        basic.setSex(this.adaptee.getPersonInfo().getSex());
        
        return basic;
    }

    public PersonExtend getExtendInfo() {
        PersonExtend extend = new PersonExtend();
        
        extend.setAddress(this.adaptee.getPersonInfo().getAddress());
        extend.setPhone(this.adaptee.getPersonInfo().getPhone());
        
        return extend;
    }

}
