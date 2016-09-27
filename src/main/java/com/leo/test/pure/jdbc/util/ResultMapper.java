package com.leo.test.pure.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Senchenko Viktor on 27.09.2016.
 */
public interface ResultMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
