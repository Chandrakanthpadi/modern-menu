package com.modernmenu.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ItemIdGenerator implements IdentifierGenerator {

  static String prefix;
  static int value;

  static {
    prefix = "I";
  }

  public ItemIdGenerator() {

    value = DBConfigAndUtil.getTableCount("item");

  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    try {

      return ItemIdGenerator.prefix + String.format("%03d", ++value);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}
