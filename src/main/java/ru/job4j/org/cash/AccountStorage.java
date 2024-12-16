package ru.job4j.org.cash;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();
    public  synchronized  boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public  synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {

        return Optional.ofNullable(accounts.get(id));
    }

    public boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromID = this.getById(fromId);
        Optional<Account> toID = this.getById(toId);
        boolean check = fromID.isEmpty() || toID.isEmpty() || fromID.get().amount() < amount;
        if (!check) {
            this.update(new Account(fromID.get().id(), fromID.get().amount() - amount));
            this.update(new Account(toID.get().id(), toID.get().amount() + amount));
        }
        return check;
    }
}
