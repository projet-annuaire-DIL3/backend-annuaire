package com.projet.annuaire.Mock;

import com.projet.annuaire.dao.SalarieDao;
import com.projet.annuaire.model.Salarie;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockSalarieDao implements SalarieDao {
    @Override
    public List<Salarie> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom) {
        if (nom.equals("TestSalarieNom")){
            return List.of(
                    new Salarie(
                            1,
                            "TestSalarieNom",
                            "TestSalariePrenom",
                            "0643616657",
                            "0643616657",
                            "testsalarie@agro.com",
                            null,
                            null,
                            null
                    ));
        }
        return List.of();
    }

    @Override
    public List<Salarie> findBySiteId(Integer siteId) {
        return List.of();
    }

    @Override
    public List<Salarie> findByServiceId(Integer serviceId) {
        return List.of();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Salarie> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Salarie> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Salarie> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Salarie getOne(Integer integer) {
        return null;
    }

    @Override
    public Salarie getById(Integer integer) {
        return null;
    }

    @Override
    public Salarie getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Salarie> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Salarie> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Salarie> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Salarie> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Salarie> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Salarie> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Salarie, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Salarie> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Salarie> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Salarie> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Salarie> findAll() {
        return List.of();
    }

    @Override
    public List<Salarie> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Salarie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Salarie> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Salarie> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Salarie> findAll(Pageable pageable) {
        return null;
    }
}
