package mum.edu.repository;

import mum.edu.model.BitcoinPrice;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BitcoinPriceRepository extends ElasticsearchRepository<BitcoinPrice, String> {

}
