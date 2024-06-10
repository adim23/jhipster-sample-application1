import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './content.reducer';

export const ContentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const contentEntity = useAppSelector(state => state.content.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contentDetailsHeading">
          <Translate contentKey="jhipsterSampleApplication1App.content.detail.title">Content</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contentEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="jhipsterSampleApplication1App.content.code">Code</Translate>
            </span>
          </dt>
          <dd>{contentEntity.code}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="jhipsterSampleApplication1App.content.title">Title</Translate>
            </span>
          </dt>
          <dd>{contentEntity.title}</dd>
          <dt>
            <span id="shortDescription">
              <Translate contentKey="jhipsterSampleApplication1App.content.shortDescription">Short Description</Translate>
            </span>
          </dt>
          <dd>{contentEntity.shortDescription}</dd>
          <dt>
            <span id="data">
              <Translate contentKey="jhipsterSampleApplication1App.content.data">Data</Translate>
            </span>
          </dt>
          <dd>{contentEntity.data}</dd>
          <dt>
            <span id="meta">
              <Translate contentKey="jhipsterSampleApplication1App.content.meta">Meta</Translate>
            </span>
          </dt>
          <dd>{contentEntity.meta}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="jhipsterSampleApplication1App.content.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {contentEntity.createdDate ? <TextFormat value={contentEntity.createdDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.language">Language</Translate>
          </dt>
          <dd>{contentEntity.language ? contentEntity.language.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.createdBy">Created By</Translate>
          </dt>
          <dd>{contentEntity.createdBy ? contentEntity.createdBy.login : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.destination">Destination</Translate>
          </dt>
          <dd>{contentEntity.destination ? contentEntity.destination.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.tourCategory">Tour Category</Translate>
          </dt>
          <dd>{contentEntity.tourCategory ? contentEntity.tourCategory.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.place">Place</Translate>
          </dt>
          <dd>{contentEntity.place ? contentEntity.place.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.placeCategory">Place Category</Translate>
          </dt>
          <dd>{contentEntity.placeCategory ? contentEntity.placeCategory.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.tourExtraCategory">Tour Extra Category</Translate>
          </dt>
          <dd>{contentEntity.tourExtraCategory ? contentEntity.tourExtraCategory.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.tourExtra">Tour Extra</Translate>
          </dt>
          <dd>{contentEntity.tourExtra ? contentEntity.tourExtra.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.menu">Menu</Translate>
          </dt>
          <dd>{contentEntity.menu ? contentEntity.menu.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.webPage">Web Page</Translate>
          </dt>
          <dd>{contentEntity.webPage ? contentEntity.webPage.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.tag">Tag</Translate>
          </dt>
          <dd>{contentEntity.tag ? contentEntity.tag.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.tourStep">Tour Step</Translate>
          </dt>
          <dd>{contentEntity.tourStep ? contentEntity.tourStep.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.promotion">Promotion</Translate>
          </dt>
          <dd>{contentEntity.promotion ? contentEntity.promotion.code : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.content.imageFile">Image File</Translate>
          </dt>
          <dd>{contentEntity.imageFile ? contentEntity.imageFile.code : ''}</dd>
        </dl>
        <Button tag={Link} to="/content" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/content/${contentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContentDetail;
