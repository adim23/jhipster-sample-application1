import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './tour-content.reducer';

export const TourContentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const tourContentEntity = useAppSelector(state => state.tourContent.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tourContentDetailsHeading">
          <Translate contentKey="jhipsterSampleApplication1App.tourContent.detail.title">TourContent</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.code">Code</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.code}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.title">Title</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.title}</dd>
          <dt>
            <span id="shortDescription">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.shortDescription">Short Description</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.shortDescription}</dd>
          <dt>
            <span id="data">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.data">Data</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.data}</dd>
          <dt>
            <span id="meta">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.meta">Meta</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.meta}</dd>
          <dt>
            <span id="cancellation">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.cancellation">Cancellation</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.cancellation}</dd>
          <dt>
            <span id="activityPath">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.activityPath">Activity Path</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.activityPath}</dd>
          <dt>
            <span id="atAGlance">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.atAGlance">At A Glance</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.atAGlance}</dd>
          <dt>
            <span id="inDetail">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.inDetail">In Detail</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.inDetail}</dd>
          <dt>
            <span id="whatIsIncluded">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.whatIsIncluded">What Is Included</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.whatIsIncluded}</dd>
          <dt>
            <span id="youCanAdd">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.youCanAdd">You Can Add</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.youCanAdd}</dd>
          <dt>
            <span id="importantInformation">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.importantInformation">Important Information</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.importantInformation}</dd>
          <dt>
            <span id="extraInfo">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.extraInfo">Extra Info</Translate>
            </span>
          </dt>
          <dd>{tourContentEntity.extraInfo}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="jhipsterSampleApplication1App.tourContent.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {tourContentEntity.createdDate ? (
              <TextFormat value={tourContentEntity.createdDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplication1App.tourContent.language">Language</Translate>
          </dt>
          <dd>{tourContentEntity.language ? tourContentEntity.language.code : ''}</dd>
        </dl>
        <Button tag={Link} to="/tour-content" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tour-content/${tourContentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TourContentDetail;
