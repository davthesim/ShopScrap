CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(512)  NOT NULL,
    description TEXT,
    image_url TEXT,
    product_url TEXT NOT NULL,
    retailer VARCHAR(255) NOT NULL,
    price_amount NUMERIC(12,2) NOT NULL,
    price_currency CHAR(3) NOT NULL,
    region VARCHAR(64),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);