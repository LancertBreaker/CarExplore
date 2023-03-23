package com.example.carexplore.login.utils;

import com.example.carexplore.login.UserBean;
import org.json.JSONObject;

public class DataUtils {
    private DataUtils mInstance;

    public DataUtils() {

    }

    public DataUtils getInstance() {
        if (mInstance == null) {
            mInstance = new DataUtils();
        }
        return mInstance;
    }

    public UserBean transferJsonToUserBean(JSONObject object) {
        UserBean bean = new UserBean();
        bean.setId(object.getInt("id"));
        bean.setName(object.getString("name"));
        bean.setPhone(object.getString("phone"));
        bean.setPassword(object.getString("password"));
        return bean;
    }

    public JSONObject transferUserBeanToJson(UserBean userBean) {
        JSONObject object = new JSONObject();
        object.put("id", userBean.getId());
        object.put("name", userBean.getName());
        object.put("phone", userBean.getPhone());
        object.put("password", userBean.getPassword());

        return object;
    }


}
