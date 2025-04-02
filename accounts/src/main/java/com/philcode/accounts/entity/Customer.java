package com.philcode.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Customer extends BaseEntity{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long customerId;
   private String name;
   private String email;
   private String mobileNumber;

   public Customer() {
   }

   public Customer(Long customerId, String name, String email, String mobileNumber) {
      this.customerId = customerId;
      this.name = name;
      this.email = email;
      this.mobileNumber = mobileNumber;
   }

   public Long getCustomerId() {
      return customerId;
   }

   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

   public String getMobileNumber() {
      return mobileNumber;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }
}
