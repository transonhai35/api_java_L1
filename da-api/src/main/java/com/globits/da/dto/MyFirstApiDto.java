package com.globits.da.dto;

public class MyFirstApiDto {
   private String code;
   private String name;
   private Integer age;

    public MyFirstApiDto() {
    }

    public MyFirstApiDto(String code, String name, Integer id) {
        this.age = age;
        this.code = code;
        this.name = name;
    }

   public MyFirstApiDto(MyFirstApiDto myFirstApiDto) {
       if (myFirstApiDto != null) {
           this.age = myFirstApiDto.getAge();
           this.code = myFirstApiDto.getCode();
           this.name = myFirstApiDto.getName();
       }
   }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyDTO{" +
                "name: '" + name + '\'' +
                "code:'" + code + '\'' +
                ", age:'" + age +
                '}';
    }
}
