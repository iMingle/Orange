package org.orange.features.java17;

/**
 * @author mingle
 */
public abstract sealed class ClosedClasses permits Dog, Cat {
}

final class Dog extends ClosedClasses {
}

final class Cat extends ClosedClasses {
}
