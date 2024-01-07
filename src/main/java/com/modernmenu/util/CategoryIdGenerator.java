package com.modernmenu.util;

import java.io.*;
import org.hibernate.*;
import org.hibernate.engine.spi.*;
import org.hibernate.id.*;

public class CategoryIdGenerator implements IdentifierGenerator {

  static int value;
  static String prefix;

  static {
    prefix = "C";
  }

  public CategoryIdGenerator() {

    value = DBConfigAndUtil.getTableCount("category");

  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    try {
      value++;
      String id = CategoryIdGenerator.prefix + String.format("%03d", value);
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
