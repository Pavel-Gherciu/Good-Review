package net.app.goodreview.controller;

import lombok.RequiredArgsConstructor;
import net.app.goodreview.dto.request.NewCompaniesDto;
import net.app.goodreview.dto.request.UpdateCompaniesDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.exception.ReviewNotFoundException;
import net.app.goodreview.model.Companies;
import net.app.goodreview.model.Review;
import net.app.goodreview.service.CompaniesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import static net.app.goodreview.service.mapper.CompaniesMapper.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompaniesController {

    private final CompaniesService companiesService;

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@PathVariable final Integer id, @Valid @RequestBody NewCompaniesDto companyDto) {
        companiesService.addCompany(toCompanies(companyDto), id);
    }

    @GetMapping("/read/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompaniesResponseDto getCompanyById(@PathVariable final Integer companyId) {
        return fromCompanies(companiesService.getCompany(companyId));
    }

    @GetMapping("/readall/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CompaniesResponseDto> getAllCompaniesByCategory(@PathVariable final Integer categoryId) {
        return fromCompaniesList(companiesService.getCompanyListByCategory(categoryId));
    }

    @GetMapping("/readall")
    @ResponseStatus(HttpStatus.OK)
    public List<CompaniesResponseDto> getAllCompanies() {
        return fromCompaniesList(companiesService.getCompanyList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {
        companiesService.deleteCompany(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompaniesResponseDto updateCompany(@PathVariable Integer id,
                                       @Valid @RequestBody UpdateCompaniesDto updateCompaniesDto) {
        return fromCompanies(companiesService.update(id, toCompanies(updateCompaniesDto), updateCompaniesDto.getCategoryId()));
    }
}
