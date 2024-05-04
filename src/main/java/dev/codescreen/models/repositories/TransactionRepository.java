//package dev.codescreen.models.repositories;
//
//import dev.codescreen.models.Transaction;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Repository
//public class TransactionRepository implements CrudRepository<Transaction, String> {
//    Set<Transaction> transactions = new HashSet();
//
//    @Override
//    public <S extends Transaction> S save(S entity) {
//        transactions.add(entity);
//        return entity;
//    }
//
//    @Override
//    public <S extends Transaction> Iterable<S> saveAll(Iterable<S> entities) {
//        for (S entry : entities) {
//            transactions.add(entry);
//        }
//        return entities;
//    }
//
//    @Override
//    public Optional<Transaction> findById(String s) {
//        for (Transaction t : transactions) {
//            if(t.getId().equals(s)) {
//                return Optional.ofNullable(t);
//            }
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        for (Transaction t : transactions) {
//            if (t.getId().equals(s)) {
//                return true;
//            }
//        }
//        return true;
//
//    }
//
//    @Override
//    public Iterable<Transaction> findAll() {
//        return new HashSet<>(transactions);
//    }
//
//    @Override
//    public Iterable<Transaction> findAllById(Iterable<String> strings) {
//        HashSet<Transaction> rets = new HashSet();
//        for (String s : strings) {
//            for (Transaction t : transactions) {
//                if(t.getId().equals(s)) {
//                    rets.add(t);
//                }
//            }
//        }
//        return rets;
//    }
//
//    @Override
//    public long count() {
//        return transactions.size();
//    }
//
//    @Override
//    public void deleteById(String s) {
//        for (Transaction t : transactions) {
//            if(t.getId().equals(s)) {
//                transactions.remove(t);
//            }
//        }
//
//    }
//
//    @Override
//    public void delete(Transaction entity) {
//        for (Transaction t : transactions) {
//            if(t.equals(entity)) {
//                transactions.remove(t);
//            }
//        }
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//        for (String s : strings) {
//            for (Transaction t : transactions) {
//                if(t.getId().equals(s)) {
//                    transactions.remove(t);
//                }
//            }
//        }
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Transaction> entities) {
//        for (Transaction t : entities) {
//            transactions.remove(t);
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        transactions = new HashSet<>();
//    }
//}
//
