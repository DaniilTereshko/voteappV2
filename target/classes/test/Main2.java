package test;

import jakarta.persistence.*;
import test.core.Task;
import test.core.User;
import test.core.UserInformation;

import java.util.Iterator;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("USERSLOCAL");
            EntityManager entityManager = emf.createEntityManager();) {

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Task task = new Task();
            task.setName("Task 1");
            Task task2 = new Task();
            task2.setName("Task 2");

            User user = new User();
            user.setUsername("Daniil23");
            user.setPassword("Tereshko");
            User user1 = new User();
            user1.setUsername(null);
            user1.setPassword("Tereshko");

            user1.setTask(task);
            user1.setTask(task2);
            user.setTask(task);



            entityManager.persist(user);
            entityManager.persist(user1);
            entityManager.flush();
            entityManager.clear();
            transaction.commit();


            EntityTransaction transaction1 = entityManager.getTransaction();
            transaction1.begin();

            Query query = entityManager.createNamedQuery("User.findAll");
            List<User> resultList = query.getResultList();
            Iterator<User> iterator = resultList.iterator();
            while (iterator.hasNext()){
                User next = iterator.next();
                System.out.println(next.getId() + " " + next.getTask());
            }


        }
    }
}
