package net.app.goodreview.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.app.goodreview.dto.request.UpdateCompaniesDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.exception.CategoryNotFoundException;
import net.app.goodreview.exception.CompanyNotFoundException;
import net.app.goodreview.exception.ReviewNotFoundException;
import net.app.goodreview.model.Categories;
import net.app.goodreview.model.Companies;
import net.app.goodreview.model.Review;
import net.app.goodreview.repository.CategoriesRepository;
import net.app.goodreview.repository.CompaniesRepository;
import net.app.goodreview.service.CompaniesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompaniesServiceImpl implements CompaniesService {
    private final CompaniesRepository companiesRepository;

    private final CategoriesRepository categoriesRepository;

    @Override
    public void addCompany(Companies company, final Integer categoryId) {
        company.setCategory(categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category for this company with id " + categoryId + " was not found")));
        companiesRepository.save(company);
    }

    @Override
    public Companies getCompany(final Integer companyId){
        Companies company = companiesRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFoundException("Company with id " + companyId + " not found"));
        return company;
    }

    @Override
    public List<Companies> getCompanyListByCategory(final Integer categoryId){
        return companiesRepository.getCompaniesByCategoryId(categoryId);
    }

    @Override
    public List<Companies> getCompanyList(){
        return companiesRepository.findAll();
    }

    @Override
    public void deleteCompany(Integer companyId) {
        Companies companiesToDelete = companiesRepository.findById(companyId).orElseThrow(() ->
                new ReviewNotFoundException("Company with id " + companyId + " was not found"));
        companiesRepository.deleteById(companiesToDelete.getId());
    }

    @Override
    public Companies update(final Integer companyId, Companies companies, final Integer categoryId) {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + categoryId + "not found"));

        Companies companyToUpdate = companiesRepository.findById(companyId).orElseThrow(() ->
                new ReviewNotFoundException("Company with id " + companyId + " was not found"));

        companyToUpdate.setLogo(companies.getLogo());
        companyToUpdate.setCity(companies.getCity());
        companyToUpdate.setAbout(companies.getAbout());
        companyToUpdate.setPhone(companies.getPhone());
        companyToUpdate.setLocation(companies.getLocation());
        companyToUpdate.setSchedule(companies.getSchedule());
        companyToUpdate.setSite(companies.getSite());
        companyToUpdate.setSiteLink(companies.getSiteLink());
        companyToUpdate.setTitle(companies.getTitle());
        companyToUpdate.setRoute(companies.getRoute());
        companyToUpdate.setTopImg(companies.getTopImg());
        companyToUpdate.setCategory(category);

        return companiesRepository.save(companyToUpdate);
    }
}
