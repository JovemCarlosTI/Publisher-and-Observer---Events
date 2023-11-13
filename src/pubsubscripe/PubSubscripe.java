/**
*
* @author jovemcarlosti
*/

package pubsubscripe;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class Publisher {
    // Lista de ouvintes
    private List<Observer> observers = new ArrayList<>();

    public void addOuvinte(Observer obsever) {
        observers.add(obsever);
    }

    public void removeOuvinte(Observer observer) {
        observers.remove(observer);
    }

    public void publish(String message) {
        System.out.println("Publicando mensagem: " + message);

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

class Ouvinte implements Observer {
    private String name;

    public Ouvinte(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ": Recebi a mensagem: " + message);
    }
}

public class PubSubscripe {
    public static void main(String[] args) {
        Publisher pub1 = new Publisher();
        Publisher pub2 = new Publisher();

        Observer sub1 = new Ouvinte("Ouvinte 1");
        Observer sub2 = new Ouvinte("Ouvinte 2");
        Observer sub3 = new Ouvinte("Ouvinte 3");

        pub1.addOuvinte(sub1);
        pub1.addOuvinte(sub2);

        pub2.addOuvinte(sub1);
        pub2.addOuvinte(sub2);
        pub2.addOuvinte(sub3);

        pub1.publish("Primeira Mensagem de PUB1");
        pub2.publish("Primeira Mensagem de PUB2");

        pub1.removeOuvinte(sub2);
        pub1.publish("Segunda Mensagem de PUB1");
    }
}
