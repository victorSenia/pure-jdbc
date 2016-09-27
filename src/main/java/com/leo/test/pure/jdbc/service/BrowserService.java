package com.leo.test.pure.jdbc.service;

import com.leo.test.pure.jdbc.model.Browser;
import com.leo.test.pure.jdbc.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public class BrowserService {
    String list = "SELECT * FROM `browser`";

    String get = "SELECT * FROM `browser` WHERE id=?";

    String delete = "DELETE FROM `browser` WHERE id=?";

    String create = "INSERT `browser` SET `browser`=?, `css_grade`=?, `engine`=?, `engine_version`=?, `platform`=?";

    String edit = "UPDATE `browser` SET `browser`=?, `css_grade`=?, `engine`=?, `engine_version`=?, `platform`=?  WHERE id=?";

    JdbcUtil jdbcUtil = new JdbcUtil();

    public Iterable<Browser> list() {
        return jdbcUtil.execute(list, resultSet -> {
            List<Browser> browserList = new ArrayList<>();
            while (resultSet.next()) {
                browserList.add(browser(resultSet));
            }
            return browserList;
        });
    }

    private Browser browser(ResultSet resultSet) throws SQLException {
        return new Browser(resultSet.getInt("id"), resultSet.getString("browser"), resultSet.getString("css_grade"), resultSet.getString("engine"), resultSet.getString("engine_version"), resultSet.getString("platform"));
    }

    public Browser get(Integer id) {
        return jdbcUtil.execute(get, resultSet -> {
            if (resultSet.next())
                return browser(resultSet);
            return null;
        }, id);
    }

    public Browser create(Browser browser) {
        return jdbcUtil.execute(create, resultSet -> {
            if (resultSet.next())
                return get(resultSet.getInt("GENERATED_KEY"));
            return null;
        }, browser.getBrowser(), browser.getCssGrade(), browser.getEngine(), browser.getEngineVersion(), browser.getPlatform());
    }

    public Browser edit(Browser browser) {
        return jdbcUtil.execute(edit, resultSet -> {
            if (!resultSet.next())
                return get(browser.getId());
            return null;
        }, browser.getBrowser(), browser.getCssGrade(), browser.getEngine(), browser.getEngineVersion(), browser.getPlatform(), browser.getId());
    }

    public void delete(Integer id) {
        jdbcUtil.execute(delete, resultSet -> null, id);
    }
}
