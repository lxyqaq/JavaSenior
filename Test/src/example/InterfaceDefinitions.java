package example;

interface Iterator {
    boolean hasNext();

    Object next();
}

interface Container {
    Iterator iterator();
}
//-----------------------------------

interface Visitor {
    public void visit(Visitable v);
}


interface Visitable {
    public void accept(Visitor v);
}
//--------------------------

