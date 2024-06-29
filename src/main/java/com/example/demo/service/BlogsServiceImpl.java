package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Blogs;
import com.example.demo.exceptions.SetUpExceptions;
import com.example.demo.model.BlogsModel;
import com.example.demo.repository.BlogsRepository;

/**
 * @author austine
 *
 */
@Service
public class BlogsServiceImpl implements BlogService {
	@Autowired
	private BlogsRepository blogsRepository;

	@Override
	@Transactional
	public Blogs getBlogsById(Long blog_id) {
		try {
			Optional<Blogs> blogs = blogsRepository.findById(blog_id);
			if (blogs.isPresent()) {
				return blogs.get();
			} else {
				throw new SetUpExceptions("Blogs not found with ID: " + blog_id);
			}
		} catch (SetUpExceptions e) {
			throw e;
		} catch (Exception e) {
			throw new SetUpExceptions("An error occurred while fetching the Blogs");
		}
	}

	@Override
	@Transactional
	public Blogs createBlogs(BlogsModel blogModel, MultipartFile file) {
		try {
			Blogs blogs = new Blogs();
			// Save the file to the server
			if (file != null && !file.isEmpty()) {
				String uploadDir = "/var/www/html/blogs/uploads"; // Destination directory
				String fileName = file.getOriginalFilename();

				// Create the directory if it doesn't exist
				Path directoryPath = Paths.get(uploadDir);
				if (!Files.exists(directoryPath)) {
					try {
						Files.createDirectories(directoryPath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// Define the path where the file will be saved
				Path filePath = Paths.get(uploadDir, fileName);

				// Save the file to the specified path
				try {
					Files.copy(file.getInputStream(), filePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				blogs.setActive(true);
				blogs.setApprovalStage("Approved");
				blogs.setName(blogModel.getName());
				blogs.setDescription(blogModel.getDescription());
				blogs.setApproved(true);
				String FILE_PATH = "http://localhost/blogs/uploads/" + fileName;
				blogs.setFilepath(FILE_PATH);
				blogs.setFileName(fileName);

	
			}
			return blogsRepository.save(blogs);
		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not create Blog");
			// TODO: handle exception
		}
	}

	@Override
	@Transactional
	public Blogs updateBlogs(BlogsModel blogModel, MultipartFile file) {
		try {
			Blogs blogs = blogsRepository.findById(blogModel.getId()).get();
			if (blogs == null) {
				throw new SetUpExceptions("Could not find the blog with id " + blogModel.getId());
			}

			if (file != null && !file.isEmpty()) {
				String uploadDir = "/var/www/html/blogs/uploads"; // Destination directory
				String fileName = file.getOriginalFilename();

				// Create the directory if it doesn't exist
				Path directoryPath = Paths.get(uploadDir);
				if (!Files.exists(directoryPath)) {
					try {
						Files.createDirectories(directoryPath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// Define the path where the file will be saved
				Path filePath = Paths.get(uploadDir, fileName);

				// Save the file to the specified path
				try {
					Files.copy(file.getInputStream(), filePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				blogs.setActive(true);
				blogs.setApprovalStage("Approved");
				blogs.setName(blogModel.getName());
				blogs.setDescription(blogModel.getDescription());
				blogs.setApproved(true);
				String FILE_PATH = "http://localhost/blogs/uploads/" + fileName;
				blogs.setFilepath(FILE_PATH);
				blogs.setFileName(fileName);

	
			}
			return blogsRepository.save(blogs);

		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not update Blog");
			// TODO: handle exception
		}
	}

	@Override
	@Transactional
	public Blogs deleteById(Long Blogs_id) {
		try {
			Blogs existEntity = blogsRepository.findById(Blogs_id).orElse(null);

			if (existEntity == null) {
				throw new SetUpExceptions("Blogs is not found");

			}
			existEntity.setActive(false);
			return blogsRepository.save(existEntity);
		} catch (ObjectNotFoundException e) {
			throw new SetUpExceptions("Could not find Blog by Id");
			// TODO: handle exception
		}
	}

	@Override
	@Transactional
	public List<Blogs> getAllBlogs() {
		try {
			return blogsRepository.findAll();

		} catch (SetUpExceptions e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not find Blogs");
		}
	}

	@Override
	@Transactional
	public List<Blogs> getBlogsByPageAndSize(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Blogs> BlogsPage = blogsRepository.findAll(pageable);
		return BlogsPage.getContent();
	}

	@Override
	@Transactional
	public List<Blogs> findByIsActive(boolean isActive) {
		try {
			return blogsRepository.findByIsActive(isActive);

		} catch (SetUpExceptions e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not find active Blogs");
		}
	}

	@Override
	public List<Blogs> findByName(String name) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return blogsRepository.findByName(name);
		} catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not find Blogs by name");
		}
	}

	@Override
	public List<Blogs> findByDescription(String desc) throws SetUpExceptions {
		// TODO Auto-generated method stub
		try {
			return blogsRepository.findByDescription(desc);
		} catch (Exception e) {
			// TODO: handle exception
			throw new SetUpExceptions("Could not find Blogs by Content");
		}
	}
	//Creating an Excell report
	/*public OutputModel generateStockTransferExcell() {
    Workbook workbook = new XSSFWorkbook();
    OutputModel model = new OutputModel();
    Sheet sheet = workbook.createSheet("Stock Transfers");
    FileOutputStream outputStream = null;
    String filePath = "/home/austine/Creswave-Projects/SWIFTSYNC/stock_transfer.xlsx";
    File file = new File(filePath);

    try {
        outputStream = new FileOutputStream(file);

        // Header for the report
        Row reportHeader = sheet.createRow(0);
        Cell reportHeaderCell = reportHeader.createCell(0);
        reportHeaderCell.setCellValue("Stock Transfers");

        CellStyle reportHeaderStyle = workbook.createCellStyle();
        Font reportHeaderFont = workbook.createFont();
        reportHeaderFont.setBold(true);
        reportHeaderFont.setFontHeightInPoints((short) 15);
        reportHeaderFont.setColor(IndexedColors.LIGHT_BLUE.getIndex());
        reportHeaderStyle.setAlignment(HorizontalAlignment.CENTER);

        reportHeaderStyle.setFont(reportHeaderFont);
        reportHeaderCell.setCellStyle(reportHeaderStyle);

        // Merge cells for the report header
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        
        // Header for the report
        Row reportHeaderDateFrom = sheet.createRow(1);
        Cell reportHeaderCellDateFrom = reportHeaderDateFrom.createCell(0);
        reportHeaderCellDateFrom.setCellValue("Date From");

        CellStyle reportHeaderStyleDateFrom = workbook.createCellStyle();
        Font reportHeaderFontDateFrom = workbook.createFont();
        reportHeaderFontDateFrom.setBold(true);
        reportHeaderFontDateFrom.setFontHeightInPoints((short) 15);
        reportHeaderFontDateFrom.setColor(IndexedColors.LIGHT_BLUE.getIndex());
        reportHeaderStyleDateFrom.setAlignment(HorizontalAlignment.CENTER);

        reportHeaderStyleDateFrom.setFont(reportHeaderFont);
        reportHeaderCellDateFrom.setCellStyle(reportHeaderStyle);

        // Merge cells for the report header
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        // Create header row for the columns
        Row headerRow = sheet.createRow(2);
        String[] columns = {"Product", "Branch From", "Store From","Debit Quantity", "Branch To", "Store To", "Credit Quantity"};
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFont(headerFont);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        // Fetch data
        List<StockTransfer> items = (List<StockTransfer>) stockTransferRepo.findAllStockTransferWhereProductToNotNull();
        int rowNum = 3;
        for (StockTransfer item : items) {
            Row row = sheet.createRow(rowNum++);
            HospitalBranch branchFrom = hospitalBranchRepository.findById(item.getBranchIdFrom());
            HospitalBranch branchTo = hospitalBranchRepository.findById(item.getBranchIdTo());
            Product product = productRepository.findById(item.getProductId());
            Store storeFrom = storeRepo.findById(item.getStoreFrom());
            Store storeTo = storeRepo.findById(item.getStoreTo());

            // Ensure that none of the entities are null before accessing their properties
            if (product != null && branchFrom != null && branchTo != null && storeFrom != null && storeTo != null) {
                row.createCell(0).setCellValue(product.getProductName());
                row.createCell(1).setCellValue(branchFrom.getBranchName());
                row.createCell(2).setCellValue(storeFrom.getStoreName());
                row.createCell(3).setCellValue(item.getDebitQuantity());
                row.createCell(4).setCellValue(branchTo.getBranchName());
                row.createCell(5).setCellValue(storeTo.getStoreName());
                row.createCell(6).setCellValue(item.getCreditQuantity());
            } else {
                // Handle null values appropriately (e.g., log a warning, skip the row, etc.)
                System.out.println("Warning: One of the entities is null for StockTransfer ID: " + item.getId());
            }
        }

        // Write the output to response
        workbook.write(outputStream);
        model.setFileUrl(filePath);
        model.setFileLocation(filePath);
    } catch (IOException e) {
        e.printStackTrace();
        // Additional error handling (e.g., log the error, rethrow the exception, etc.)
    } finally {
        try {
            if (workbook != null) {
                workbook.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    return model;
} */



}
