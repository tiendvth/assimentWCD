package fptaptech.assignmentwcd.entity;

import fptaptech.assignmentwcd.constant.ValidationConstant;
import fptaptech.assignmentwcd.entity.base.BaseEntity;
import fptaptech.assignmentwcd.entity.entityEnum.AccountStatus;
import fptaptech.assignmentwcd.util.ValidationStringHelper;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Account extends BaseEntity {
    private int id;
    private String fullName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private int roleId;
    private AccountStatus status;
    private HashMap<String, String> errors;

    public Account() {
    }

    public Account(String fullName, String username, String email, String phoneNumber, String password, String confirmPassword, int roleId, AccountStatus status) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
        this.confirmPassword = confirmPassword;
    }

    public Account(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, int createdBy, int updatedBy, int deletedBy, int id, String fullName, String username, String email, String phoneNumber, String password, int roleId, AccountStatus status) {
        super(createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy);
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public static final class AccountBuilder {
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
        public LocalDateTime deletedAt;
        public int createdBy;
        public int updatedBy;
        public int deletedBy;
        private int id;
        private String fullName;
        private String username;
        private String email;
        private String phoneNumber;
        private String password;
        private String confirmPassword;
        private int roleId;
        private AccountStatus status;
        private HashMap<String, String> errors;

        private AccountBuilder() {
            this.fullName = "";
            this.email = "";
            this.phoneNumber = "";
            this.username = "";
            this.password = "";
            this.confirmPassword = "";
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            this.status = AccountStatus.ACTIVE;
            this.errors = new HashMap<>();
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AccountBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AccountBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public AccountBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder withConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public AccountBuilder withRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public AccountBuilder withStatus(AccountStatus status) {
            this.status = status;
            return this;
        }

        public AccountBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AccountBuilder withDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public AccountBuilder withCreatedBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public AccountBuilder withUpdatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public AccountBuilder withDeletedBy(int deletedBy) {
            this.deletedBy = deletedBy;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.setId(id);
            account.setFullName(fullName);
            account.setUsername(username);
            account.setEmail(email);
            account.setPhoneNumber(phoneNumber);
            account.setPassword(password);
            account.setConfirmPassword(confirmPassword);
            account.setRoleId(roleId);
            account.setStatus(status);
            account.setCreatedAt(createdAt);
            account.setUpdatedAt(updatedAt);
            account.setDeletedAt(deletedAt);
            account.setCreatedBy(createdBy);
            account.setUpdatedBy(updatedBy);
            account.setDeletedBy(deletedBy);
            account.setErrors(errors);
            return account;
        }
    }

    public boolean isValid() {
        this.checkValid();
        return errors.size() == 0;
    }

    public void checkValid() {
        if(this.fullName.equals("") || this.fullName == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_FULL_NAME, ValidationConstant.ACCOUNT_ERROR_MESSAGE_FULL_NAME_REQUIRED);
        }
        if(this.username.equals("") || this.username == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_USERNAME, ValidationConstant.ACCOUNT_ERROR_MESSAGE_USERNAME_REQUIRED);
        }
        if(this.email.equals("") || this.email == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_EMAIL, ValidationConstant.ACCOUNT_ERROR_MESSAGE_EMAIL_REQUIRED);
        }
        if(!this.email.equals("") && this.email != null && !ValidationStringHelper.validateEmail(this.email)) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_EMAIL, ValidationConstant.ACCOUNT_ERROR_MESSAGE_EMAIL_INVALID);
        }
        if(this.phoneNumber.equals("") || this.phoneNumber == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_PHONE_NUMBER, ValidationConstant.ACCOUNT_ERROR_MESSAGE_PHONE_NUMBER_REQUIRED);
        }
        if(!this.phoneNumber.equals("") && this.phoneNumber != null && !ValidationStringHelper.validatePhone(this.phoneNumber)) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_PHONE_NUMBER, ValidationConstant.ACCOUNT_ERROR_MESSAGE_PHONE_NUMBER_INVALID);
        }
        if(this.password.equals("") || this.password == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_PASSWORD, ValidationConstant.ACCOUNT_ERROR_MESSAGE_PASSWORD_REQUIRED);
        }
        if(this.confirmPassword.equals("") || this.confirmPassword == null) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_CONFIRM_PASSWORD, ValidationConstant.ACCOUNT_ERROR_MESSAGE_CONFIRM_PASSWORD_REQUIRED);
        }
        if(!this.confirmPassword.equals("") && this.confirmPassword != null && !this.confirmPassword.equals(this.password)) {
            errors.put(ValidationConstant.ACCOUNT_ERROR_KEY_CONFIRM_PASSWORD, ValidationConstant.ACCOUNT_ERROR_MESSAGE_CONFIRM_PASSWORD_NOT_MATCH);
        }
    }
}