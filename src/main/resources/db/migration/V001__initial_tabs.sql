-- PostgreSQL Script convertido de MySQL
-- Autor: Jonathan Nascimento "ntobase"

-- ============================================
-- 3. TABELA: tb_user
-- ============================================

CREATE TABLE IF NOT EXISTS tb_user (
  id_usr BIGSERIAL PRIMARY KEY,
  nam_usr VARCHAR(255) NOT NULL,
  cpf_usr VARCHAR(11) NOT NULL,
  eml_usr VARCHAR(255) NOT NULL,
  ph_num_usr VARCHAR(20),
  str_add_usr VARCHAR(255),
  add_num_usr BIGINT,
  city_usr VARCHAR(100),
  sta_usr VARCHAR(50),
  zip_code_usr VARCHAR(20),
  birth_date_usr TIMESTAMP,
  user_nam_usr VARCHAR(100) NOT NULL,
  pass_usr VARCHAR(255) NOT NULL,
  sec_quest_usr VARCHAR(255),
  sec_answ_usr VARCHAR(255),
  user_type_usr VARCHAR(50),
  crt_at_dat_usr TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  sts_usr VARCHAR(20) DEFAULT 'active',
  pro_pic_user BYTEA,
  addit_notes_usr TEXT
);

-- Comentários de coluna
COMMENT ON COLUMN tb_user.id_usr IS 'Identificador único do usuário / Unique user ID';
COMMENT ON COLUMN tb_user.nam_usr IS 'Nome completo do usuário / Full name';
COMMENT ON COLUMN tb_user.cpf_usr IS 'CPF do usuário / User CPF';
COMMENT ON COLUMN tb_user.eml_usr IS 'Email do usuário / User email';
COMMENT ON COLUMN tb_user.ph_num_usr IS 'Telefone do usuário / Phone number';
COMMENT ON COLUMN tb_user.str_add_usr IS 'Rua / Street address';
COMMENT ON COLUMN tb_user.add_num_usr IS 'Número do endereço / Address number';
COMMENT ON COLUMN tb_user.city_usr IS 'Cidade do endereço / City';
COMMENT ON COLUMN tb_user.sta_usr IS 'Estado do endereço / State';
COMMENT ON COLUMN tb_user.zip_code_usr IS 'CEP do endereço / Zip code';
COMMENT ON COLUMN tb_user.birth_date_usr IS 'Data de nascimento / Birth date';
COMMENT ON COLUMN tb_user.user_nam_usr IS 'Nome de usuário / Username';
COMMENT ON COLUMN tb_user.pass_usr IS 'Senha do usuário / Password';
COMMENT ON COLUMN tb_user.sec_quest_usr IS 'Pergunta de segurança / Security question';
COMMENT ON COLUMN tb_user.sec_answ_usr IS 'Resposta da pergunta de segurança / Security answer';
COMMENT ON COLUMN tb_user.user_type_usr IS 'Tipo de usuário / User type';
COMMENT ON COLUMN tb_user.crt_at_dat_usr IS 'Data de criação do registro / Creation date';
COMMENT ON COLUMN tb_user.sts_usr IS 'Status do usuário / User status';
COMMENT ON COLUMN tb_user.pro_pic_user IS 'Foto de perfil / Profile picture';
COMMENT ON COLUMN tb_user.addit_notes_usr IS 'Notas adicionais / Additional notes';

CREATE UNIQUE INDEX eml_usr_idx ON tb_user (eml_usr);
CREATE UNIQUE INDEX user_nam_usr_idx ON tb_user (user_nam_usr);

-- ============================================
-- 4. TABELA: tb_cash_register
-- ============================================

CREATE TABLE IF NOT EXISTS tb_cash_register (
  id_csh_reg BIGSERIAL PRIMARY KEY,
  init_dt_csh_reg TIMESTAMP NOT NULL,
  end_dt_csh_reg TIMESTAMP,
  init_blc_csh_reg DECIMAL(10,2) NOT NULL,
  end_blc_csh_reg DECIMAL(10,2),
  total_sale_csh_reg DECIMAL(10,2),
  total_with_csh_reg DECIMAL(10,2),
  status_csh_reg VARCHAR(20) NOT NULL DEFAULT 'open',
  notes_csh_reg TEXT,
  id_usr BIGINT NOT NULL
);

-- Comentários
COMMENT ON COLUMN tb_cash_register.id_csh_reg IS 'Identificador do caixa / Cash register ID';
COMMENT ON COLUMN tb_cash_register.init_dt_csh_reg IS 'Data de abertura / Opening date';
COMMENT ON COLUMN tb_cash_register.end_dt_csh_reg IS 'Data de fechamento / Closing date';
COMMENT ON COLUMN tb_cash_register.init_blc_csh_reg IS 'Saldo inicial / Initial balance';
COMMENT ON COLUMN tb_cash_register.end_blc_csh_reg IS 'Saldo final / Closing balance';
COMMENT ON COLUMN tb_cash_register.total_sale_csh_reg IS 'Total de vendas / Total sales';
COMMENT ON COLUMN tb_cash_register.total_with_csh_reg IS 'Total de retiradas / Total withdrawals';
COMMENT ON COLUMN tb_cash_register.status_csh_reg IS 'Status do caixa / Register status';
COMMENT ON COLUMN tb_cash_register.notes_csh_reg IS 'Notas sobre o caixa / Notes';
COMMENT ON COLUMN tb_cash_register.id_usr IS 'ID do usuário responsável / User ID';

CREATE INDEX fk_tb_cash_register_tb_user_idx ON tb_cash_register (id_usr);

-- ============================================
-- 5. TABELA: tb_payment_type
-- ============================================

CREATE TABLE IF NOT EXISTS tb_payment_type (
  id_pay_tp BIGSERIAL PRIMARY KEY,
  name_pay_tp VARCHAR(50) NOT NULL,
  desc_pay_tp VARCHAR(45)
);

COMMENT ON COLUMN tb_payment_type.id_pay_tp IS 'Identificador do tipo de pagamento / Payment type ID';
COMMENT ON COLUMN tb_payment_type.name_pay_tp IS 'Nome do tipo de pagamento / Name (e.g., PIX, Cartão)';
COMMENT ON COLUMN tb_payment_type.desc_pay_tp IS 'Descrição do tipo de pagamento / Description';

-- ============================================
-- 6. TABELA: tb_payment
-- ============================================

CREATE TABLE IF NOT EXISTS tb_payment (
  id_pay BIGSERIAL PRIMARY KEY,
  total_pay DECIMAL(10,2) NOT NULL,
  dt_ini_pay TIMESTAMP NOT NULL,
  dt_fin_pay TIMESTAMP,
  sts_pay VARCHAR(45) NOT NULL,
  id_pay_tp BIGINT NOT NULL
);

COMMENT ON COLUMN tb_payment.id_pay IS 'Identificador do pagamento / Payment ID';
COMMENT ON COLUMN tb_payment.total_pay IS 'Valor total pago / Total amount paid';
COMMENT ON COLUMN tb_payment.dt_ini_pay IS 'Data/hora de início / Start date';
COMMENT ON COLUMN tb_payment.dt_fin_pay IS 'Data/hora final / End date';
COMMENT ON COLUMN tb_payment.sts_pay IS 'Status do pagamento / Payment status';
COMMENT ON COLUMN tb_payment.id_pay_tp IS 'ID do tipo de pagamento / Payment type ID';

CREATE INDEX fk_tb_payment_tb_payment_type1_idx ON tb_payment (id_pay_tp);

-- ============================================
-- 7. TABELA: tb_dish
-- ============================================

CREATE TABLE IF NOT EXISTS tb_dish (
  id_dsh BIGSERIAL PRIMARY KEY,
  dish_nam_dsh VARCHAR(255) NOT NULL,
  dish_prc_dsh DECIMAL(10,2) NOT NULL,
  desc_dsh TEXT NOT NULL,
  cat_dsh VARCHAR(100) NOT NULL,
  has_add_dsh CHAR(1) DEFAULT 'n',
  pic_dsh BYTEA
);

COMMENT ON COLUMN tb_dish.id_dsh IS 'Identificador do lanche / Dish ID';
COMMENT ON COLUMN tb_dish.dish_nam_dsh IS 'Nome do lanche / Dish name';
COMMENT ON COLUMN tb_dish.dish_prc_dsh IS 'Preço / Price';
COMMENT ON COLUMN tb_dish.desc_dsh IS 'Descrição do lanche / Description';
COMMENT ON COLUMN tb_dish.cat_dsh IS 'Categoria / Category';
COMMENT ON COLUMN tb_dish.has_add_dsh IS 'Possui adicionais? / Has add-ons (y/n)';
COMMENT ON COLUMN tb_dish.pic_dsh IS 'Imagem do lanche / Dish picture';

-- ============================================
-- 8. TABELA: tb_order
-- ============================================

CREATE TABLE IF NOT EXISTS tb_order (
  id_ord BIGSERIAL,
  status_ord VARCHAR(20),
  date_ord TIMESTAMP NOT NULL,
  tab_num_ord BIGINT NOT NULL,  -- Alterado de INT para BIGINT
  total_prc_ord DECIMAL(10,2),
  paymt_sts_ord VARCHAR(20) NOT NULL DEFAULT 'unpaid',
  end_date_ord TIMESTAMP,
  notes_ord TEXT,
  id_csh_reg BIGINT NOT NULL,   -- Alterado para BIGINT
  id_usr BIGINT NOT NULL,       -- Alterado para BIGINT
  id_pay BIGINT,                -- Alterado para BIGINT
  id_dsh BIGINT,                -- Alterado para BIGINT
  PRIMARY KEY (id_ord, id_csh_reg, id_usr)
);

-- Comentários atualizados
COMMENT ON COLUMN tb_order.id_ord IS 'ID da comanda / Order ID';
COMMENT ON COLUMN tb_order.status_ord IS 'Status da comanda / Order status';
COMMENT ON COLUMN tb_order.date_ord IS 'Data/hora da comanda / Order date';
COMMENT ON COLUMN tb_order.tab_num_ord IS 'Número da mesa / Table number (BIGINT)';
COMMENT ON COLUMN tb_order.total_prc_ord IS 'Preço total / Total price';
COMMENT ON COLUMN tb_order.paymt_sts_ord IS 'Status de pagamento / Payment status';
COMMENT ON COLUMN tb_order.end_date_ord IS 'Data de fechamento / End date';
COMMENT ON COLUMN tb_order.notes_ord IS 'Notas adicionais / Notes';
COMMENT ON COLUMN tb_order.id_csh_reg IS 'ID do caixa / Cash register ID (BIGINT)';
COMMENT ON COLUMN tb_order.id_usr IS 'ID do usuário responsável / User ID (BIGINT)';
COMMENT ON COLUMN tb_order.id_pay IS 'ID do pagamento / Payment ID (BIGINT)';
COMMENT ON COLUMN tb_order.id_dsh IS 'ID do lanche / Dish ID (BIGINT)';

-- Índices
CREATE INDEX fk_tb_order_tb_cash_register1_idx ON tb_order (id_csh_reg);
CREATE INDEX fk_tb_order_tb_payment1_idx ON tb_order (id_pay);
CREATE INDEX fk_tb_order_tb_dish1_idx ON tb_order (id_dsh);



-- ============================================
-- 9. CONSTRAINTS de CHAVES ESTRANGEIRAS
-- ============================================

ALTER TABLE tb_cash_register
  ADD CONSTRAINT fk_tb_cash_register_tb_user
  FOREIGN KEY (id_usr) REFERENCES tb_user (id_usr)
  ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE tb_payment
  ADD CONSTRAINT fk_tb_payment_tb_payment_type1
  FOREIGN KEY (id_pay_tp) REFERENCES tb_payment_type (id_pay_tp)
  ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE tb_order
  ADD CONSTRAINT fk_tb_order_tb_cash_register1
  FOREIGN KEY (id_csh_reg) REFERENCES tb_cash_register (id_csh_reg)
  ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE tb_order
  ADD CONSTRAINT fk_tb_order_tb_payment1
  FOREIGN KEY (id_pay) REFERENCES tb_payment (id_pay)
  ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE tb_order
  ADD CONSTRAINT fk_tb_order_tb_dish1
  FOREIGN KEY (id_dsh) REFERENCES tb_dish (id_dsh)
  ON DELETE NO ACTION ON UPDATE NO ACTION;