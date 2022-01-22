package ru.job4j.forum.temp;

import ru.job4j.forum.repository.HbmRepository;

public class Test3 {

    public static void main(String[] args) {
        HbmRepository hbmRepository = new HbmRepository();
       var rsl = hbmRepository.findByNameUser("Ivan Sobolev");
        System.out.println(rsl);
    }
}
