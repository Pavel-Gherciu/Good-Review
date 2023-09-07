package net.app.goodreview.repository;

import net.app.goodreview.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompaniesRepository extends JpaRepository<Companies, Integer> {

    List<Companies> getCompaniesByCategoryId(Integer categoryId);
}
