package net.app.goodreview.service;

import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.model.Companies;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompaniesService {

    void addCompany(Companies company, final Integer categoryId);

    Companies getCompany(final Integer companyId);

    List<Companies> getCompanyList();

    List<Companies> getCompanyListByCategory(final Integer categoryId);

    void deleteCompany(Integer companyId);

    Companies update(final Integer companyId, Companies companies, final Integer categoryId);
}
