/*
# Add Product Status and Metadata

1. Changes:
   - Add status enum type
   - Add status column to products
   - Add metadata columns
   - Add audit triggers
*/

-- Create product status enum
DO $$ BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_type WHERE typname = 'product_status'
    ) THEN
        CREATE TYPE product_status AS ENUM ('ACTIVE', 'INACTIVE', 'DISCONTINUED');
    END IF;
END $$;

-- Add status column to products
ALTER TABLE products
ADD COLUMN status product_status NOT NULL DEFAULT 'ACTIVE';

-- Add metadata columns to products
ALTER TABLE products
ADD COLUMN brand VARCHAR(100),
ADD COLUMN manufacturer VARCHAR(200),
ADD COLUMN weight DECIMAL(10,2),
ADD COLUMN dimensions VARCHAR(50),
ADD COLUMN tags TEXT[];

-- Create index for status and tags
CREATE INDEX idx_products_status ON products(status);
CREATE INDEX idx_products_tags ON products USING gin(tags);