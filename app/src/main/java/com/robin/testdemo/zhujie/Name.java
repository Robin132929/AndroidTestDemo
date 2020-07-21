package com.robin.testdemo.zhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Names.class)
public @interface Name {
    Class getclass();
    String getname();
    info getenum();

    public enum info{
        Name("class"),
        Type("abc");
      String abc;
        info(String abc) {
            this.abc=abc;
        }

        @Override
        public String toString() {
            return "info{" +
                    "abc='" + abc + '\'' +
                    '}';
        }
    }
}
