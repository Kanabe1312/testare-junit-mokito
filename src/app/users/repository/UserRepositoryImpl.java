package app.users.repository;

import app.users.factory.UserFactory;
import app.users.factory.UserFactorySingleton;
import app.users.model.User;
import app.system.utils.DataPaths;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class UserRepositoryImpl implements UserRepository{

    private List<User> users;
    String filename;
    private final UserFactory userFactory;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
        userFactory = UserFactorySingleton.getINSTANCE();
        filename = DataPaths.of("src", "app", "users", "data", "Users.txt");
        this.loadData();
    }



    public void loadData() {
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.users.add(userFactory.createFromText(line));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void saveData() {
        File file = new File(filename);

        try{
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(this);
            printWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
       return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findAny();


    }


    private int generateUniqueId() {
        Random random = new Random();
        int generatedId = random.nextInt();

        while (findUserById(generatedId).isPresent()){
            generatedId = random.nextInt();
        }

        return generatedId;
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<User> findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    @Override
    public User saveUser(User user){
        user.setId(generateUniqueId());
        this.users.add(user);
        saveData();

        return user;
    }

    @Override
    public User updateUser(User user) {
        saveData();
        return user;
    }

    @Override
    public String toString() {

        StringBuilder text;
        text = new StringBuilder();
        int i;
        for( i=0 ;i<users.size()-1; i++){
            text.append(users.get(i)+"\n");
        }
        text.append(users.get(i));
        return String.valueOf(text);
    }

    @Override
    public List<User> listUsers() {

        return users;

    }


}
