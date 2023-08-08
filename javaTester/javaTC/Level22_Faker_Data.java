package javaTC;

import java.util.Locale;

import com.github.javafaker.Faker;

public class Level22_Faker_Data {
	public static void main(String[] args) {
		Faker faker = new Faker();
		System.out.println(faker.address().firstName());
		System.out.println(faker.address().lastName());
		System.out.println(faker.business().creditCardType());

		Faker fakeVi = new Faker(new Locale("vi"));
		System.out.println(fakeVi.address().firstName());
		System.out.println(fakeVi.address().lastName());
		System.out.println(fakeVi.business().creditCardType());
	}
}
