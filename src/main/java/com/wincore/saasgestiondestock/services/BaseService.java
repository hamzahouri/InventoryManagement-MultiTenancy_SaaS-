package com.wincore.saasgestiondestock.services;

import java.util.List;

public interface BaseService <I,O>{

    void create (final I request);

    void update (final Long id, final I request);

    List<O> findAll (final int page, final int size);

    O findById (final Long  id);

    void delete (final Long id);
}
