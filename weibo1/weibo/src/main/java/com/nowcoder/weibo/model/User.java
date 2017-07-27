package com.nowcoder.weibo.model;

/**
 * Created by soyo pu on 2017/7/25.
 */
    public class User {
        private int id;
        private String name;
        private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;
        private String salt;
        private String headUrl;

        public User(){

        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public String getSalt() {
            return salt;
        }



        public void setSalt(String salt) {
            this.salt = salt;
        }


    }


