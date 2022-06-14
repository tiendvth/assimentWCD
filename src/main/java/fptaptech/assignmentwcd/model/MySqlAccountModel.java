package fptaptech.assignmentwcd.model;

import fptaptech.assignmentwcd.constant.SqlConstant;
import fptaptech.assignmentwcd.entity.Account;
import fptaptech.assignmentwcd.entity.entityEnum.AccountStatus;
import fptaptech.assignmentwcd.model.interfaceModel.AccountModel;
import fptaptech.assignmentwcd.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountModel implements AccountModel {
    @Override
    public boolean create(Account account) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_INSERT);
            preparedStatement.setString(1, account.getFullName());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setInt(5, account.getRoleId());
            preparedStatement.setString(6, account.getPhoneNumber());
            preparedStatement.setString(7, account.getCreatedAt().toString());
            preparedStatement.setString(8, account.getUpdatedAt().toString());
            preparedStatement.setInt(9, account.getCreatedBy());
            preparedStatement.setInt(10, account.getUpdatedBy());
            preparedStatement.setInt(11, account.getStatus().getValue());
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Account account) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_UPDATE);
            preparedStatement.setString(1, account.getFullName());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setInt(5, account.getRoleId());
            preparedStatement.setString(6, account.getPhoneNumber());
            preparedStatement.setString(7, account.getCreatedAt().toString());
            preparedStatement.setString(8, account.getUpdatedAt().toString());
            preparedStatement.setString(9, account.getDeletedAt().toString());
            preparedStatement.setInt(10, account.getCreatedBy());
            preparedStatement.setInt(11, account.getUpdatedBy());
            preparedStatement.setInt(12, account.getDeletedBy());
            preparedStatement.setInt(13, account.getStatus().getValue());
            preparedStatement.setInt(14, id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_DELETE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account findById(int id) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                return resultSetToAccount(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account findByUsername(String username) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                return resultSetToAccount(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.ACCOUNT_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account account = resultSetToAccount(rs);
                if(account != null) {
                    accounts.add(account);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account resultSetToAccount(ResultSet rs)
    {
        try{
            int id = Integer.parseInt(rs.getString(SqlConstant.ACCOUNT_FIELD_ID));
            String fullName = rs.getString(SqlConstant.ACCOUNT_FIELD_FULL_NAME);
            String username = rs.getString(SqlConstant.ACCOUNT_FIELD_USERNAME);
            String email = rs.getString(SqlConstant.ACCOUNT_FIELD_EMAIL);
            String phoneNumber = rs.getString(SqlConstant.ACCOUNT_FIELD_PHONE_NUMBER);
            String password = rs.getString(SqlConstant.ACCOUNT_FIELD_PASSWORD);
            int roleId = Integer.parseInt(rs.getString(SqlConstant.ACCOUNT_FIELD_ROLE_ID));
            LocalDateTime createdAt = rs.getTimestamp(SqlConstant.FIELD_CREATED_AT).toLocalDateTime();
            LocalDateTime updatedAt = rs.getTimestamp(SqlConstant.FIELD_UPDATED_AT).toLocalDateTime();
            LocalDateTime deletedAt = null;
            if(rs.getTimestamp(SqlConstant.FIELD_DELETED_AT) != null) {
                deletedAt = rs.getTimestamp(SqlConstant.FIELD_DELETED_AT).toLocalDateTime();
            }
            int createdBy = rs.getInt(SqlConstant.FIELD_CREATED_BY);
            int updatedBy = rs.getInt(SqlConstant.FIELD_UPDATED_BY);
            int deletedBy = rs.getInt(SqlConstant.FIELD_DELETED_BY);
            AccountStatus status = AccountStatus.of(rs.getInt(SqlConstant.ACCOUNT_FIELD_STATUS));
            return Account.AccountBuilder.anAccount()
                    .withId(id)
                    .withFullName(fullName)
                    .withUsername(username)
                    .withEmail(email)
                    .withPhoneNumber(phoneNumber)
                    .withPassword(password)
                    .withRoleId(roleId)
                    .withCreatedAt(createdAt)
                    .withUpdatedAt(updatedAt)
                    .withDeletedAt(deletedAt)
                    .withCreatedBy(createdBy)
                    .withUpdatedBy(updatedBy)
                    .withDeletedBy(deletedBy)
                    .withStatus(status)
                    .build();
        }catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}