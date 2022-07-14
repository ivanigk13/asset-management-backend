package com.lawencon.asset.dao.impl.hibernatenative;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.File;

@Profile("native")
@Repository
public class AssetDaoHibernateNativeImpl extends BaseDaoImpl implements AssetDao{

	@Override
	public List<Asset> getAll() throws IOException {
		String sql = "SELECT a.id, a.file_id, a.asset_name, a.serial, a.asset_description, a.invoice, a.expired, "
				+ "a.created_by, a.created_at, a.updated_by, a.updated_at, a.version, a.is_active,"
				+ "c.company_name, ass.alt_name, ast.type_name, b.brand_name, f.content "
				+ "FROM asset a "
				+ "LEFT JOIN company c ON a.company_id = c.id "
				+ "LEFT JOIN asset_status ass ON a.asset_status_id = ass.id "
				+ "LEFT JOIN asset_type ast ON a.asset_type_id = ast.id "
				+ "LEFT JOIN brand b ON a.brand_id = b.id "
				+ "LEFT JOIN file f ON a.file_id = f.id "
				+ "ORDER BY a.id ASC";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Asset> assets = new ArrayList<Asset>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[0].toString()));
			File file = new File();
			if(obj[1]!=null) {
				file.setId(Long.valueOf(obj[1].toString()));
				file.setContent(convertObjToByteArray(obj[17]));
			}
			asset.setFile(file);
			asset.setAssetName(obj[2].toString());
			asset.setSerial(obj[3].toString());
			asset.setAssetDescription(obj[4].toString());
			asset.setInvoice(obj[5].toString());
			if(obj[6]!=null)asset.setExpired(((Date)obj[6]).toLocalDate());
			else asset.setExpired(null);
			
			if(obj[7]!=null)asset.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)asset.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());			
			
			if(obj[9]!=null) asset.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) asset.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
			
			asset.setVersion(Integer.valueOf(obj[11].toString()));
			asset.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			Company company = new Company();
			company.setCompanyName(obj[13].toString());
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setAltName(obj[14].toString());
			AssetType assetType = new AssetType();
			assetType.setTypeName(obj[15].toString());
			Brand brand = new Brand();
			brand.setBrandName(obj[16].toString());
			
			asset.setCompany(company);
			asset.setAssetStatus(assetStatus);
			asset.setAssetType(assetType);
			asset.setBrand(brand);			
			assets.add(asset);
		}
		return assets;
	}

	@Override
	public Asset save(Asset asset) {
		if(asset.getId()!=null) {
			asset = em.merge(asset);
			em.flush();
		}else {
			em.persist(asset);
		}
		em.detach(asset);
		return asset;
	}

	@Override
	public Asset getById(Long id) {
		Asset asset = em.find(Asset.class, id);
		em.detach(asset);
		return asset;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM asset WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

	@Override
	public List<Asset> getReadyToUse() throws IOException {
		String sql = "SELECT a.id, a.file_id, a.asset_name, a.serial, a.asset_description, a.invoice, a.expired, "
				+ "a.created_by, a.created_at, a.updated_by, a.updated_at, a.version, a.is_active, "
				+ "c.company_name, ass.alt_name, ast.type_name, b.brand_name, f.content "
				+ "FROM asset a "
				+ "LEFT JOIN company c ON a.company_id = c.id "
				+ "LEFT JOIN asset_status ass ON a.asset_status_id = ass.id "
				+ "LEFT JOIN asset_type ast ON a.asset_type_id = ast.id "
				+ "LEFT JOIN brand b ON a.brand_id = b.id "
				+ "LEFT JOIN file f ON a.file_id = f.id "
				+ "WHERE asset_status_id = (SELECT id FROM asset_status WHERE status_code = 'RD') "
				+ "ORDER BY a.id ASC";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Asset> assets = new ArrayList<Asset>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[0].toString()));
			File file = new File();
			if(obj[1]!=null) {
				file.setId(Long.valueOf(obj[1].toString()));
				file.setContent(convertObjToByteArray(obj[17]));
			}
			asset.setFile(file);
			asset.setAssetName(obj[2].toString());
			asset.setSerial(obj[3].toString());
			asset.setAssetDescription(obj[4].toString());
			asset.setInvoice(obj[5].toString());
			if(obj[6]!=null)asset.setExpired(((Date)obj[6]).toLocalDate());
			else asset.setExpired(null);
			
			if(obj[7]!=null)asset.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)asset.setCreatedAt(((LocalDateTime)obj[8]));			
			
			if(obj[9]!=null) asset.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) asset.setUpdatedAt(((LocalDateTime)obj[10]));
			
			asset.setVersion(Integer.valueOf(obj[11].toString()));
			asset.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			Company company = new Company();
			company.setCompanyName(obj[13].toString());
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setAltName(obj[14].toString());
			AssetType assetType = new AssetType();
			assetType.setTypeName(obj[15].toString());
			Brand brand = new Brand();
			brand.setBrandName(obj[16].toString());
			
			asset.setCompany(company);
			asset.setAssetStatus(assetStatus);
			asset.setAssetType(assetType);
			asset.setBrand(brand);			
			assets.add(asset);
		}
		return assets;
	}

	@Override
	public List<Asset> getExpiredAsset() throws IOException {
		String sql = "SELECT a.id, a.file_id, a.asset_name, a.serial, a.asset_description, a.invoice, a.expired, "
				+ "a.created_by, a.created_at, a.updated_by, a.updated_at, a.version, a.is_active, "
				+ "c.company_name, ass.alt_name, ast.type_name, b.brand_name, f.content "
				+ "FROM asset a "
				+ "LEFT JOIN company c ON a.company_id = c.id "
				+ "LEFT JOIN asset_status ass ON a.asset_status_id = ass.id "
				+ "LEFT JOIN asset_type ast ON a.asset_type_id = ast.id "
				+ "LEFT JOIN brand b ON a.brand_id = b.id "
				+ "LEFT JOIN file f ON a.file_id = f.id "
				+ "WHERE expired <= NOW() "
				+ "ORDER BY a.id";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Asset> assets = new ArrayList<Asset>();
		for (Object result : results) {
			Object[] obj = (Object[]) result;
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[0].toString()));
			File file = new File();
			if(obj[1]!=null) {
				file.setId(Long.valueOf(obj[1].toString()));
				file.setContent(convertObjToByteArray(obj[17]));
			}
			asset.setFile(file);
			asset.setAssetName(obj[2].toString());
			asset.setSerial(obj[3].toString());
			asset.setAssetDescription(obj[4].toString());
			asset.setInvoice(obj[5].toString());
			if(obj[6]!=null)asset.setExpired(((Date)obj[6]).toLocalDate());
			else asset.setExpired(null);
			
			if(obj[7]!=null)asset.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)asset.setCreatedAt(((LocalDateTime)obj[8]));			
			
			if(obj[9]!=null) asset.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) asset.setUpdatedAt(((LocalDateTime)obj[10]));
			
			asset.setVersion(Integer.valueOf(obj[11].toString()));
			asset.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			Company company = new Company();
			company.setCompanyName(obj[13].toString());
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setAltName(obj[14].toString());
			AssetType assetType = new AssetType();
			assetType.setTypeName(obj[15].toString());
			Brand brand = new Brand();
			brand.setBrandName(obj[16].toString());
			
			asset.setCompany(company);
			asset.setAssetStatus(assetStatus);
			asset.setAssetType(assetType);
			asset.setBrand(brand);			
			assets.add(asset);
		}
		return assets;
	}
	
	private byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
	}

}
