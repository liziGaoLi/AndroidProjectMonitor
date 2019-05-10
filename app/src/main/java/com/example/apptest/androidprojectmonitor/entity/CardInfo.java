package com.example.apptest.androidprojectmonitor.entity;

public class CardInfo {
    /**
     * flag : 1
     * obj : {"cardtype":"TF","sex":null,"policeCode":"06","police":"出入境管理","depCode":"220000060000","name":"张鸿雁","depName":"吉林省公安厅出入境管理局","grade":"5","code":"000559","identifier":"220181197501110221","mobile":"18043005510"}
     */

    private String flag;
    private ObjBean obj;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * cardtype : TF
         * sex : null
         * policeCode : 06
         * police : 出入境管理
         * depCode : 220000060000
         * name : 张鸿雁
         * depName : 吉林省公安厅出入境管理局
         * grade : 5
         * code : 000559
         * identifier : 220181197501110221
         * mobile : 18043005510
         */

        private String cardtype;
        private Object sex;
        private String policeCode;
        private String police;
        private String depCode;
        private String name;
        private String depName;
        private String grade;
        private String code;
        private String identifier;
        private String mobile;

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getPoliceCode() {
            return policeCode;
        }

        public void setPoliceCode(String policeCode) {
            this.policeCode = policeCode;
        }

        public String getPolice() {
            return police;
        }

        public void setPolice(String police) {
            this.police = police;
        }

        public String getDepCode() {
            return depCode;
        }

        public void setDepCode(String depCode) {
            this.depCode = depCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
