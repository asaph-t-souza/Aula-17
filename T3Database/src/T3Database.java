import java.sql.*;
import java.util.Scanner;

public class T3Database {
    private Scanner myScanner = new Scanner(System.in);
    private Connection myConnection;
    private PreparedStatement insertStatement;
    private PreparedStatement selectStatement;
    private PreparedStatement selectUserStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;

    public T3Database() throws SQLException {
        // URL do BD "meu_banco"
        String url = "jdbc:postgresql://localhost:5432/meu_banco";

        // pede o usuario administrador do banco
        System.out.print("Enter admin username: ");
        String adminUsername = myScanner.nextLine();

        // pede a senha do administrador do banco
        System.out.print("Enter admin password: ");
        String password = myScanner.nextLine();

        // cria a conexão
        myConnection = DriverManager.getConnection(url, adminUsername, password);
        // prepara o statement insertStatement
        insertStatement = myConnection.prepareStatement("INSERT INTO clients VALUES (?, ?, ?, ?, ?)");
        selectStatement = myConnection.prepareStatement("SELECT * FROM clients");
        selectUserStatement = myConnection.prepareStatement("SELECT * FROM clients WHERE username = ?");
        updateStatement = myConnection.prepareStatement(
                "UPDATE clients SET username = ?, fullname = ?, email = ?, phone = ?, age = ? WHERE username = ?");
        deleteStatement = myConnection.prepareStatement("DELETE FROM clients WHERE username = ?");
    }

    public void insertOperation() {
        System.out.println("Enter client info");

        try {
            // insere o usuario no index 1 do statement
            System.out.print("Enter client username: ");
            insertStatement.setString(1, myScanner.nextLine());
            // insere o nome completo no index 2 do statement
            System.out.print("Enter client full name: ");
            insertStatement.setString(2, myScanner.nextLine());
            // insere o email no index 3 do statement
            System.out.print("Enter client e-mail: ");
            insertStatement.setString(3, myScanner.nextLine());
            // insere o celular no index 4 do statement
            System.out.print("Enter client phone: ");
            insertStatement.setString(4, myScanner.nextLine());
            // insere a idade no index 5 do statement
            System.out.print("Enter client age: ");
            insertStatement.setInt(5, myScanner.nextInt());
            // executo o meu statement insert
            int response = insertStatement.executeUpdate();
            System.out.println("Lines Inserted: " + response);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectOperation() {
        System.out.println("List of clients:");

        try {
            ResultSet tableResult = selectStatement.executeQuery();

            while (tableResult.next()) {
                Client oneClient = new Client(
                        tableResult.getString(1).trim(),
                        tableResult.getString(2).trim(),
                        tableResult.getString(3).trim(),
                        tableResult.getString(4).trim(),
                        tableResult.getInt(5));

                System.out.println(oneClient.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOperation() {
        System.out.println("Enter the username of the client to be updated: ");
        Client updateClient = null;
        String username = null;

        // Seleciona o cliente pelo nome de usuario e o guarda na variavel updateCLient
        try {
            username = myScanner.nextLine();
            selectUserStatement.setString(1, username);
            ResultSet clientInfo = selectUserStatement.executeQuery();

            if (clientInfo.next()) {
                updateClient = new Client(
                        clientInfo.getString(1).trim(),
                        clientInfo.getString(2).trim(),
                        clientInfo.getString(3).trim(),
                        clientInfo.getString(4).trim(),
                        clientInfo.getInt(5));

                System.out.println(updateClient.toString());
            } else {
                System.out.println("A client with the username " + username + " doesn't exists.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Boolean continueOperation = false;

        System.out.println();
        System.out.println("Enter the new username:");
        System.out.println("Leave it blank for no alteration");
        System.out.println("Actual username: " + updateClient.getUsername());

        // enquanto continue operation for falso, o loop se repetira
        continueOperation = false;
        while (continueOperation == false) {
            try {
                String newUsername = myScanner.nextLine();

                // se a string passada for igual a "" ou a operação de set não jpgar uma
                // exceção, o loop while é quebrado
                if (newUsername != "") {
                    updateClient.setUsername(newUsername);
                    System.out.println("New username: " + updateClient.getUsername());
                    continueOperation = true;
                } else {
                    System.out.println("No alteration, username: " + updateClient.getUsername());
                    continueOperation = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try to input the new username again:");
            }
        }

        System.out.println();
        System.out.println("Enter the new client full name:");
        System.out.println("Leave it blank for no alteration");
        System.out.println("Actual full name: " + updateClient.getFullName());

        // enquanto continue operation for falso, o loop se repetira
        continueOperation = false;
        while (continueOperation == false) {
            try {
                String newFullName = myScanner.nextLine();

                // se a string passada for igual a "" ou a operação de set não jogar uma
                // exceção, o loop while é quebrado
                if (newFullName != "") {
                    updateClient.setFullName(newFullName);
                    System.out.println("New full name: " + updateClient.getFullName());
                    continueOperation = true;
                } else {
                    System.out.println("No alteration, full name: " + updateClient.getFullName());
                    continueOperation = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try to input the new full name again:");
            }
        }

        System.out.println();
        System.out.println("Enter the new client email:");
        System.out.println("Leave it blank for no alteration");
        System.out.println("Actual email: " + updateClient.getEmail());

        // enquanto continue operation for falso, o loop se repetira
        continueOperation = false;
        while (continueOperation == false) {
            try {
                String newEmail = myScanner.nextLine();

                // se a string passada for igual a "" ou a operação de set não jogar uma
                // exceção, o loop while é quebrado
                if (newEmail != "") {
                    updateClient.setEmail(newEmail);
                    System.out.println("New email: " + updateClient.getEmail());
                    continueOperation = true;
                } else {
                    System.out.println("No alteration, email: " + updateClient.getEmail());
                    continueOperation = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try to input the new email again:");
            }
        }

        System.out.println();
        System.out.println("Enter the new client phone:");
        System.out.println("Leave it blank for no alteration");
        System.out.println("Actual phone: " + updateClient.getPhone());

        // enquanto continue operation for falso, o loop se repetira
        continueOperation = false;
        while (continueOperation == false) {
            try {
                String newPhone = myScanner.nextLine();

                // se a string passada for igual a "" ou a operação de set não jogar uma
                // exceção, o loop while é quebrado
                if (newPhone != "") {
                    updateClient.setPhone(newPhone);
                    System.out.println("New Phone: " + updateClient.getPhone());
                    continueOperation = true;
                } else {
                    System.out.println("No alteration, Phone: " + updateClient.getPhone());
                    continueOperation = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try to input the new Phone again:");
            }
        }

        System.out.println();
        System.out.println("Enter the new client Age:");
        System.out.println("Leave it blank for no alteration");
        System.out.println("Actual Age: " + updateClient.getAge());

        // enquanto continue operation for falso, o loop se repetira
        continueOperation = false;
        while (continueOperation == false) {
            try {
                String newAge = myScanner.nextLine();

                // se a string passada for igual a "" ou a operação de set não jogar uma
                // exceção, o loop while é quebrado
                if (newAge != "") {
                    updateClient.setAge(Integer.parseInt(newAge));
                    System.out.println("New Age: " + updateClient.getAge());
                    continueOperation = true;
                } else {
                    System.out.println("No alteration, Age: " + updateClient.getAge());
                    continueOperation = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try to input the new Age again:");
            }
        }

        System.out.println();
        System.out.println("Update client: " + username + " y/n");
        try {

            String option = myScanner.nextLine();
            if (option.equals("y") || option.equals("Y")) {
                updateStatement.setString(1, updateClient.getUsername());
                updateStatement.setString(2, updateClient.getFullName());
                updateStatement.setString(3, updateClient.getEmail());
                updateStatement.setString(4, updateClient.getPhone());
                updateStatement.setInt(5, updateClient.getAge());
                updateStatement.setString(6, username);
                int results = updateStatement.executeUpdate();

                if(results == 1){
                    System.out.println("1 row updated");
                }
   
            } else {
                System.out.println("Update Canceled");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteOperation() {
        System.out.println("Enter the username of the client to be deleted: ");
        try {
            String username = myScanner.nextLine();
            deleteStatement.setString(1, username);
            int results = deleteStatement.executeUpdate();

            if (results == 1) {
                System.out.println(username + " was removed from the clients table.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
