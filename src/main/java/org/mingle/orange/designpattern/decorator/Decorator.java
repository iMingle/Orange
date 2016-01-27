package org.mingle.orange.designpattern.decorator;

public class Decorator extends SchoolReport {
    private SchoolReport sr;

    public Decorator(SchoolReport sr) {
        super();
        this.sr = sr;
    }

    @Override
    public void report() {
        // TODO Auto-generated method stub
        this.sr.report();
    }

    @Override
    public void sign(String name) {
        // TODO Auto-generated method stub
        this.sr.sign(name);
    }
    
}
